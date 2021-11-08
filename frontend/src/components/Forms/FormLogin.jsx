import styles from "./styles.module.css"
import { Link } from "react-router-dom"
import { useState } from "react";
import ValidCredentials from "../../credentials/ValidCredentials";

export default function FormLogin( { setActiveLogin, setActiveCreate, setLog } ){
    const[email, setEmail] = useState({campo:"", valido:true});
    const[password, setPassword] = useState({campo:"", valido:true});
    const[error, setError] = useState("")
    const[formValido, setFormValido]=useState(false)

    setActiveCreate(false)
    setActiveLogin(true)

    /*CONTROL DE COMPONENTES MEDIANTE HANDLES */
    const handleChangeEmail = (event) => {
        setEmail({...email, campo:event.target.value})
    }
    
    const handleChangePassword = (event) => {
        setPassword({...password, campo:event.target.value})
    }

    /*VALIDACIONES */
    const validarEmail = () => {
        setEmail({...email, valido:true})
        setError("")
        if((email.campo)!== ValidCredentials.email){
            setError("Por favor vuelva a intentarlo. Las credenciales son inválidas")
            setEmail({...email, valido:false})
        }
    }

    const validarPassword = () => {
        setPassword({...password, valido:true})
        setError("")
        if((password.campo)!== ValidCredentials.password){
            setError("Por favor vuelva a intentarlo. Las credenciales son inválidas")
            setPassword({...password, valido:false})
        }
    }

    const sendData = (event) => {
        event.preventDefault();
        validarEmail();
        validarPassword();
        if(email.valido && password.valido){
            setFormValido(true)
            setLog(true)
            sessionStorage.setItem("log", "true")
            window.location.pathname = "/"
        }else{
            setFormValido(false)
        }
    }
    console.log(email);
    console.log(password);
    
    return(
        
        <div className={styles.container}>
            <h3>Iniciar sesión</h3>
            <form className={`${styles.formFlex} ${styles.login}`} onSubmit={sendData}>
                <div className={`${styles.inputLabel} ${!email.valido?styles.inputError:null}`}>
                    <label for="email">Correo electrónico</label>
                    <input type="email" name="email" id="email" value = {email.campo} onKeyUp={validarEmail} onChange={handleChangeEmail}/>
                </div>
                <div className={`${styles.inputLabel} ${!password.valido?styles.inputError:null}`}>
                    <label for="password">Contraseña</label>
                    <input type="password" name="password" id="password" value = {password.campo} onKeyUp={validarPassword} onChange = {handleChangePassword}/>
                </div>
                {!formValido && <div className={styles.errorContainer}><p className={styles.error}>{error}</p></div>}
                <div className={`${styles.inputLabel} ${styles.boton}`}>
                    <button type="submit">Ingresar</button>
                    <p>¿Aún no tenés cuenta?<Link to="/create"> Registrate</Link></p>
                </div>
            </form>
        </div>
    )
}