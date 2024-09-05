<template>
<div class="settings_body">
    <div class="settings_area">
        <div class="drop_wrap">
            <Dropdown 
                class="dropdown"
                placeholder="Select Team"
            />
        </div>
        <div class="drop_wrap">
            Pick Team 1
            <Dropdown
                class="dropdown"
                placeholder="Select Pick"  />
            <Dropdown
                class="dropdown"
                placeholder="Select Pick"  />
            <Dropdown
                class="dropdown"
                placeholder="Select Pick" />
            <Dropdown
                class="dropdown"
                placeholder="Select Pick" />
            <Dropdown
                class="dropdown"
                placeholder="Select Pick" />
        </div>
    </div>
    <div class="settings_area">
        <div class="heading_wrap"> Score</div>
        <div class="horizontal_align">
            <InputNumber 
                class="input"
            />
            <InputNumber
                class="input"
            />
        </div>
        <div class="heading_wrap"> MVP</div>
        <div class="horizontal_align">
            <div class="drop_wrap">
                Ban Team 2
                <Dropdown
                    class="dropdown"
                    placeholder="Select Ban" />
                <Dropdown
                    class="dropdown"
                    placeholder="Select Ban" />
                <Dropdown
                    class="dropdown"
                    placeholder="Select Ban" />
                <Dropdown
                    class="dropdown"
                    placeholder="Select Ban" />
                <Dropdown
                    class="dropdown"
                    placeholder="Select Ban" />
            </div>
        <div class="drop_wrap">
            <div>
                Ban Team 1
                <Dropdown
                    class="dropdown"
                    placeholder="Select Ban" />
                <Dropdown
                    class="dropdown"
                    placeholder="Select Ban" />
                <Dropdown
                    class="dropdown"
                    placeholder="Select Ban" />
                <Dropdown
                    class="dropdown"
                    placeholder="Select Ban" />
                <Dropdown
                    class="dropdown"
                    placeholder="Select Ban" />
            </div>
        </div>
        <InputNumber
                class="input"
            />
        <Button 
            class="button"
            label="Submit"
        />
    </div>
    <div class="settings_area">
        <div class="drop_wrap">
            <Dropdown 
                class="dropdown"
                placeholder="Select Team"
            />
        </div>
        <div class="drop_wrap">
            Pick Team 2
            <Dropdown
                class="dropdown"
                placeholder="Select Pick"  />
            <Dropdown
                class="dropdown"
                placeholder="Select Pick"  />
            <Dropdown
                class="dropdown"
                placeholder="Select Pick" />
            <Dropdown
                class="dropdown"
                placeholder="Select Pick" />
            <Dropdown
                class="dropdown"
                placeholder="Select Pick" />
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

.input {
    display: flex;
    justify-content: space-between;
    align-items: center;
    align-self: stretch;
    border-radius: 8px;
    background: #0D0D0D;
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