import React from 'react';
import {FormData} from '../../utils/utils';
import styles from './Form.module.scss';

type FormProp = {
    handleSubmit: (event: React.FormEvent<HTMLFormElement>) => void
    onChange:(event: React.ChangeEvent<HTMLInputElement>) => void
    initialFormData: FormData
}

export const Form = (prop: FormProp) => {
    return(
        <form onSubmit={prop.handleSubmit} className={styles.form}>
            <input type="text" name="userName" placeholder="Name"  onChange={prop.onChange}  value={prop.initialFormData.userName} required className={styles.children}/>
            <input type="text" name="message" placeholder="Message" onChange={prop.onChange} value={prop.initialFormData.message} required className={styles.children}/> 
            <input type="text" name="mood" placeholder="Mood 😁 " onChange={prop.onChange} value={prop.initialFormData.mood} required className={styles.children}/>
            <input type="text" name="whatIPlanToAchieve" placeholder="Objectives"  onChange={prop.onChange} value={prop.initialFormData.whatIPlanToAchieve} required className={styles.children}/>
            <input type="text" name="regrets"  placeholder="Regrets ☹️ ?" onChange={prop.onChange}  value={prop.initialFormData.regrets} className={styles.children}/>
            <input type="submit" className={styles.submit}/>
        </form>
    )
}