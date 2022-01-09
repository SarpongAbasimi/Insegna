import React from "react";
import {FormData} from "../../utils/utls"
import styles from './Form.module.scss'

type FormProp = {
    handleSubmit: (event:  React.FormEvent<HTMLFormElement>) => void,
    onChange: (event: React.ChangeEvent<HTMLInputElement>) => void,
    initialFormData: FormData
}

export const Form = (props: FormProp) => {
    return (
            <form onSubmit={props.handleSubmit} className={styles.form}>
                <input type="text" name="userName" placeholder="Name"  onChange={props.onChange}  value={props.initialFormData.userName} required className={styles.children}/>

                <input type="text" name="message" placeholder="Message" onChange={props.onChange} value={props.initialFormData.message} required className={styles.children}/> 

                <input type="text" name="mood" placeholder="Mood 😁 " onChange={props.onChange} value={props.initialFormData.mood} required className={styles.children}/>

                <input type="text" name="whatIPlanToAchieve" placeholder="Objectives"  onChange={props.onChange} value={props.initialFormData.whatIPlanToAchieve} required className={styles.children}/>

                 <input type="text" name="regrets"  placeholder="Regrets ☹️ ?" onChange={props.onChange}  value={props.initialFormData.regrets} className={styles.children}/>
  
                <input type="submit" className={styles.submit}/>
            </form>
    )
}
