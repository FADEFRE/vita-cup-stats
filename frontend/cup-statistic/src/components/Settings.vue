<script setup>
import { ref, onMounted, toRaw } from "vue";
import { getAllTeams, getAllChamps, postMatch } from "@/requests/requests.js"

const props = defineProps({
    streamSlotId: {
        type: String,
        required: true,
    },
    streamSlotName: {
        type: String,
        required: true,
    },
})


let teamOptions = []
const teamOptionsRef = ref([])

let champOptions = []
const champOptionsRef = ref([])


const mvp = ref()

const teamOne = ref()
const teamOne_Score = ref()
const teamOne_Pick_1 = ref()
const teamOne_Pick_2 = ref()
const teamOne_Pick_3 = ref()
const teamOne_Pick_4 = ref()
const teamOne_Pick_5 = ref()
const teamOne_Ban_1 = ref()
const teamOne_Ban_2 = ref()
const teamOne_Ban_3 = ref()
const teamOne_Ban_4 = ref()
const teamOne_Ban_5 = ref()

const teamTwo = ref()
const teamTwo_Score = ref()
const teamTwo_Pick_1 = ref()
const teamTwo_Pick_2 = ref()
const teamTwo_Pick_3 = ref()
const teamTwo_Pick_4 = ref()
const teamTwo_Pick_5 = ref()
const teamTwo_Ban_1 = ref()
const teamTwo_Ban_2 = ref()
const teamTwo_Ban_3 = ref()
const teamTwo_Ban_4 = ref()
const teamTwo_Ban_5 = ref()

let allCorrect = true

onMounted(() => {
    setTimeout(() => {
        mount()
        
    }, 100);
})

async function mount() {
    teamOptions = await getAllTeams()
    teamOptionsRef.value = teamOptions

    champOptions = await getAllChamps()
    champOptionsRef.value = champOptions
}

function clicked() {
    sendData()
    //resetAllInputs()
}

async function sendData() {
    allCorrect = true
    console.log("pressed")

    const teamOneId = convertRefId(teamOne)

    const teamOneScore = convertRefNormal(teamOne_Score)

    const teamOne_Picks = [
        convertRefId(teamOne_Pick_1),
        convertRefId(teamOne_Pick_2),
        convertRefId(teamOne_Pick_3),
        convertRefId(teamOne_Pick_4),
        convertRefId(teamOne_Pick_5)
    ]
    const teamOne_Bans = [
        convertRefId(teamOne_Ban_1),
        convertRefId(teamOne_Ban_2),
        convertRefId(teamOne_Ban_3),
        convertRefId(teamOne_Ban_4),
        convertRefId(teamOne_Ban_5),
    ]

    const teamTwoId = convertRefId(teamTwo)

    const teamTwoScore = convertRefNormal(teamTwo_Score)

    const teamTwo_Picks = [
        convertRefId(teamTwo_Pick_1),
        convertRefId(teamTwo_Pick_2),
        convertRefId(teamTwo_Pick_3),
        convertRefId(teamTwo_Pick_4),
        convertRefId(teamTwo_Pick_5),
    ]
    const teamTwo_Bans = [
        convertRefId(teamTwo_Ban_1),
        convertRefId(teamTwo_Ban_2),
        convertRefId(teamTwo_Ban_3),
        convertRefId(teamTwo_Ban_4),
        convertRefId(teamTwo_Ban_5),
    ]

    const mvpValue = convertRefNormal(mvp)


    if (allCorrect) {
        postMatch(teamOneId, teamOneScore, teamOne_Picks, teamOne_Bans, teamTwoId, teamTwoScore, teamTwo_Picks, teamTwo_Bans, mvpValue)
        
        setTimeout(() => {
            resetAllInputs()
            
        }, 1000);
        
        return
    }
    else {
        return console.log("missing inputs")
    }
}

function convertRefNormal(ref) {
    if (toRaw(ref.value) !== undefined ) {
        console.log(toRaw(ref.value))
        return toRaw(ref.value)
    } else {
        allCorrect = false
        return null
    }
}

function convertRefId(ref) {
    if (toRaw(ref.value) !== undefined ) {
        console.log(toRaw(ref.value.name))
        return toRaw(ref.value.id)
    } else {
        allCorrect = false
        return null
    }
}

function resetAllInputs() {
    mvp = undefined

    teamOne = undefined
    teamOne_Score = undefined
    teamOne_Pick_1 = undefined
    teamOne_Pick_2 = undefined
    teamOne_Pick_3 = undefined
    teamOne_Pick_4 = undefined
    teamOne_Pick_5 = undefined
    teamOne_Ban_1 = undefined
    teamOne_Ban_2 = undefined
    teamOne_Ban_3 = undefined
    teamOne_Ban_4 = undefined
    teamOne_Ban_5 = undefined

    teamTwo = undefined
    teamTwo_Score = undefined
    teamTwo_Pick_1 = undefined
    teamTwo_Pick_2 = undefined
    teamTwo_Pick_3 = undefined
    teamTwo_Pick_4 = undefined
    teamTwo_Pick_5 = undefined
    teamTwo_Ban_1 = undefined
    teamTwo_Ban_2 = undefined
    teamTwo_Ban_3 = undefined
    teamTwo_Ban_4 = undefined
    teamTwo_Ban_5 = undefined
}
</script>


<template>
<div class="settings_body">
    <div class="settings_area">
        <div class="drop_wrap">
            Team 1
            <Dropdown 
                v-model="teamOne"
                :options="teamOptionsRef" 
                optionLabel="discordName" 
                filter  
                class="dropdown"
                placeholder="Select Team"
            />
        </div>

        <div class="drop_wrap">
            Ban Team 1
            <Dropdown
                v-model="teamOne_Ban_1"
                :options="champOptionsRef" 
                optionLabel="name" 
                filter 
                class="dropdown"
                placeholder="Select Ban" />
            <Dropdown
                v-model="teamOne_Ban_2"
                :options="champOptionsRef" 
                optionLabel="name" 
                filter 
                class="dropdown"
                placeholder="Select Ban" />
            <Dropdown
                v-model="teamOne_Ban_3"
                :options="champOptionsRef" 
                optionLabel="name" 
                filter 
                class="dropdown"
                placeholder="Select Ban" />
            <Dropdown
                v-model="teamOne_Ban_4"
                :options="champOptionsRef" 
                optionLabel="name" 
                filter 
                class="dropdown"
                placeholder="Select Ban" />
            <Dropdown
                v-model="teamOne_Ban_5"
                :options="champOptionsRef" 
                optionLabel="name" 
                filter 
                class="dropdown"
                placeholder="Select Ban" />
        </div>
    </div>

    <div class="settings_area">

        <div class="heading_wrap"> {{ props.streamSlotName }}</div>

        <div class="horizontal_align">
            <InputNumber
                v-model="teamOne_Score"
                class="input"
            />
            <InputNumber
                v-model="teamTwo_Score"
                class="input"
            />
        </div>

        <div class="heading_wrap"> MVP</div>
        <InputText  
                v-model="mvp"
                class="input"
            />

        <div class="horizontal_align">
            <div class="drop_wrap">
                Pick Team 1
                <Dropdown
                    v-model="teamOne_Pick_1"
                    :options="champOptionsRef" 
                    optionLabel="name"
                    filter  
                    class="dropdown"
                    placeholder="Select Pick"  />
                <Dropdown
                    v-model="teamOne_Pick_2"
                    :options="champOptionsRef" 
                    optionLabel="name" 
                    filter  
                    class="dropdown"
                    placeholder="Select Pick"  />
                <Dropdown
                    v-model="teamOne_Pick_3"
                    :options="champOptionsRef" 
                    optionLabel="name" 
                    filter  
                    class="dropdown"
                    placeholder="Select Pick" />
                <Dropdown
                    v-model="teamOne_Pick_4"
                    :options="champOptionsRef" 
                    optionLabel="name" 
                    filter  
                    class="dropdown"
                    placeholder="Select Pick" />
                <Dropdown
                    v-model="teamOne_Pick_5"
                    :options="champOptionsRef" 
                    optionLabel="name" 
                    filter  
                    class="dropdown"
                    placeholder="Select Pick" />
            </div>
            <div class="drop_wrap">
                Pick Team 2
                <Dropdown
                    v-model="teamTwo_Pick_1"
                    :options="champOptionsRef" 
                    optionLabel="name" 
                    filter  
                    class="dropdown"
                    placeholder="Select Pick"  />
                <Dropdown
                    v-model="teamTwo_Pick_2"
                    :options="champOptionsRef" 
                    optionLabel="name" 
                    filter  
                    class="dropdown"
                    placeholder="Select Pick"  />
                <Dropdown
                    v-model="teamTwo_Pick_3"
                    :options="champOptionsRef" 
                    optionLabel="name" 
                    filter  
                    class="dropdown"
                    placeholder="Select Pick" />
                <Dropdown
                    v-model="teamTwo_Pick_4"
                    :options="champOptionsRef" 
                    optionLabel="name" 
                    filter  
                    class="dropdown"
                    placeholder="Select Pick" />
                <Dropdown
                    v-model="teamTwo_Pick_5"
                    :options="champOptionsRef" 
                    optionLabel="name" 
                    filter  
                    class="dropdown"
                    placeholder="Select Pick" />
            </div>
        </div>

        <Button 
            class="button"
            label="Submit"
            @click="clicked()"
        />
    </div>

    <div class="settings_area">

        <div class="drop_wrap">
            Team 2
            <Dropdown 
                v-model="teamTwo"
                :options="teamOptionsRef" 
                optionLabel="discordName" 
                filter  
                class="dropdown"
                placeholder="Select Team"
            />
        </div>
        
        <div class="drop_wrap">
            Ban Team 2
            <Dropdown
                v-model="teamTwo_Ban_1"
                :options="champOptionsRef" 
                optionLabel="name" 
                filter  
                class="dropdown"
                placeholder="Select Ban" />
            <Dropdown
                v-model="teamTwo_Ban_2"
                :options="champOptionsRef" 
                optionLabel="name" 
                filter  
                class="dropdown"
                placeholder="Select Ban" />
            <Dropdown
                v-model="teamTwo_Ban_3"
                :options="champOptionsRef" 
                optionLabel="name" 
                filter  
                class="dropdown"
                placeholder="Select Ban" />
            <Dropdown
                v-model="teamTwo_Ban_4"
                :options="champOptionsRef" 
                optionLabel="name" 
                filter  
                class="dropdown"
                placeholder="Select Ban" />
            <Dropdown
                v-model="teamTwo_Ban_5"
                :options="champOptionsRef" 
                optionLabel="name" 
                filter  
                class="dropdown"
                placeholder="Select Ban" />
        </div>
    </div>
</div>
</template>

<style>
.settings_body {
    display: flex;
    justify-content: center;
    align-items: flex-start;
    gap: 16px;
    align-self: stretch;
}

.settings_area {
    display: flex;
    flex-direction: column;
    justify-content: stretch;
    align-items: flex-start;
    gap: 16px;
    flex: 1 0 0;
}

.drop_wrap {
    display: flex;
    padding: 32px;
    flex-direction: column;
    align-items: flex-start;
    gap: 8px;
    align-self: stretch;
    border-radius: 16px;
    background: #1A1A1A;
    width: 100%;
}

.dropdown {
    display: flex;
    padding: 16px;
    justify-content: space-between;
    align-items: center;
    align-self: stretch;
    border-radius: 8px;
    background: #0D0D0D;
    border: none;
}

.dropdown span{
    color: white;
}

.input {
    display: flex;
    justify-content: space-between;
    align-items: center;
    align-self: stretch;
    border-radius: 8px;
    background: #ffffff;
    border: none;
    width: 100%;
}

.heading_wrap {
    display: flex;
    padding: 8px;
    flex-direction: column;
    align-items: center;
    gap: 8px;
    align-self: stretch;
    border-radius: 16px;
    background: #1A1A1A;
    font-size: 32px;
    font-weight: 600;
    color: white;
}

.horizontal_align {
    display: flex;
    align-items: stretch;
    gap: 16px;
    align-self: stretch;
}

.button {
    align-self: stretch;
    border-radius: 16px;
    background: #AF0;
    border: none;
    color: #1A1A1A;
    font-size: 32px;

    font-weight: 600;
}
</style>