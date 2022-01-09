import React from "react";
import {Form}  from "../../components/form/Form";
import { useState } from "react";
import {FormData} from "../../utils/utls"
import styles from './ThoughtsPage.module.scss'
import { Link } from "react-router-dom";

export const ThoughtsPage = () => {

    let emptyFormData = {
        userName: '',
        message: '',
        mood: '',
        whatIPlanToAchieve: '',
        regrets: ''
    }

    const [initialFormData, updatedFormData] = useState<FormData>(emptyFormData)

    const postData = (event:FormData) => {
        return fetch("http://localhost:8080/api/v1/event", {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(event)
        })
    }

    const handleSubmit = (event:  React.FormEvent<HTMLFormElement>) => {
        postData(initialFormData).then(_ => updatedFormData(emptyFormData))
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
        <div className={styles.main}>

            <li className={styles.search}>
                <Link to="/">Search 🕵🏿‍♂️</Link>
            </li>

            <div className={styles.children}>
                <Form 
                handleSubmit={handleSubmit} 
                onChange={onChange} 
                initialFormData={initialFormData}
                />
            </div>
        </div>
    )
}