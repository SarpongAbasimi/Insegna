import React from "react";
import {Form}  from "../components/form/Form";
import { useState } from "react";
import {FormData} from "../utils/utls"

export const ThoughtsPage = () => {

    const [initialFormData, updatedFormData] = useState<FormData>({
        name: "",
        message: "",
        mood: "",
        whatIPlanToAchieve: "",
        regret: ""
    })

    const handleSubmit = (event: React.SyntheticEvent) => {
        event.preventDefault()
    }

    const onChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        updatedFormData({
            ...initialFormData,
            [event.target.name]: event.target.value
        })
        event.preventDefault()
    }

    return(
        <div>
            <Form 
            handleSubmit={handleSubmit} 
            onChange={onChange} 
            initialFormData={initialFormData}
            />
        </div>
    )
}