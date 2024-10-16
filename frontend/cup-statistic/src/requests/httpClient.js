import axios from "axios";

const isHandlerEnabled = (config = {}) => {
  return config.hasOwnProperty("handlerEnabled") && !config.handlerEnabled
    ? false
    : true;
};

const instance = axios.create({
  baseURL: "http://localhost:8090",
  withCredentials: false,
  timeout: 10000,
});


function create503() {
  return {
      status: "SERVICE_UNAVAILABLE",
      statusCode: 503,
      timestamp: new Date(),
      message: "Server is not responding.."
  };
}

function parseApierror(error) {
  console.log("parseapierror \n" + error);
  try {
      if (error && error.hasOwnProperty("response") && error.response.hasOwnProperty("data")) {
          const apierror = error.response.data;
          return {
              status: error.code,
              statusCode: apierror.status,
              timestamp: apierror.timestamp,
              message: apierror.message
          };
      } else {
          return create503();
      }
  }
  catch (parseError) {
      return create503();
  }
}

const requestHandler = (request) => {
  if (isHandlerEnabled(request)) {
      // console.log(request.method.toUpperCase() + "-Request: " + request.url, "  Request-Data: " + request.data );
  }
  return request;
};

const errorHandler = (error) => {  

  if (isHandlerEnabled(error.config)) {
    console.log("Error Interceptor");

    const apiError = parseApierror(error)
    console.log(apiError, "error");

  }

  return Promise.reject({ ...error });

};

const successHandler = (response) => {
  if (isHandlerEnabled(response.config)) {

      // console.log("Response: " + response.status + " ", " " + response.request.responseURL + "  Data: " + response.data);

  }
  return response;
};

instance.interceptors.request.use((request) => requestHandler(request));

instance.interceptors.response.use(
  (response) => successHandler(response),
  (error) => errorHandler(error)
);

export default instance;
