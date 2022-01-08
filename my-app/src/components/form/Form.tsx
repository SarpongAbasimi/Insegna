import React from "react";
import {FormData} from "../../utils/utls"


type FormProp = {
    handleSubmit: (event: React.SyntheticEvent) => void,
    onChange: (event: React.ChangeEvent<HTMLInputElement>) => void,
    initialFormData: FormData
}

export const Form = (props: FormProp) => {
    return (
    <div>
        <form onSubmit={props.handleSubmit}>
            <input type="text" name="name" placeholder="Name"  onChange={props.onChange}  value={props.initialFormData.name} required/>
            <input type="text" name="message" placeholder="Message" onChange={props.onChange} value={props.initialFormData.message} required/>
            <input type="text" name="mood" placeholder="Mood 😁 " onChange={props.onChange} value={props.initialFormData.mood} required/>
            <input type="text" name="whatIPlanToAchieve" placeholder="Objectives"  onChange={props.onChange} value={props.initialFormData.whatIPlanToAchieve} required/>
            <input type="text" name="regrets"  placeholder="Regrets ☹️ ?" onChange={props.onChange}  value={props.initialFormData.regret}/>
            <input type="submit"/>
        </form>
    </div>
    )
}
