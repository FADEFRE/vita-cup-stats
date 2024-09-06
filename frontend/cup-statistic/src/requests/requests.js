import httpClient from "@/requests/httpClient";

function getAllTeams() {
    console.log("getAllTeams")

    return httpClient.get('/api/team')
        .then(response => response.data)
        .catch(_ => {
        })
}

function getAllChamps() {
    console.log("getAllChamps")

    return httpClient.get('/api/champion')
        .then(response => response.data)
        .catch(_ => {
        })
}

async function postMatch(teamOneId, teamOneScore, teamOne_Picks, teamOne_Bans, teamTwoId, teamTwoScore, teamTwo_Picks, teamTwo_Bans, mvpValue) {
    console.log("postMatch")
    const formData = new FormData()
    formData.append("teamOne_id", teamOneId)
    formData.append("teamOne_Score", teamOneScore)

    formData.append("teamTwo_id", teamTwoId)
    formData.append("teamTwo_Score", teamTwoScore)
    
    formData.append("mvp", mvpValue)

    teamOne_Picks.forEach((pick, index) => {
        formData.append(`teamOne_Pick_ids[${index}]`, pick)
    });

    teamOne_Bans.forEach((ban, index) => {
        formData.append(`teamOne_Ban_ids[${index}]`, ban)
    });

    teamTwo_Picks.forEach((pick, index) => {
        formData.append(`teamTwo_Pick_ids[${index}]`, pick)
    });

    teamTwo_Bans.forEach((ban, index) => {
        formData.append(`teamTwo_Ban_ids[${index}]`, ban)
    });

    console.log(formData)

    return httpClient.post('/api/match', formData)
        .then(response => response.data)
        .catch(_ => {
        })
}

export {
    getAllTeams,
    getAllChamps,
    postMatch,
}