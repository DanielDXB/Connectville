import React, { useState } from "react";
import Button from "react-bootstrap/Button";
import "./login.css";

export default function Login() {
  const [emailState, setEmailState] = useState("");
  const [passwordState, setPasswordState] = useState("");
  const [loggedUser, setLoggedUser] = useState("");
  function sendData() {
    const data = { email: emailState, password: passwordState };
    
    console.log(JSON.stringify(data));
    useEffect(() => {
      const fetchData = async () => {
          const res = await fetch('http://localhost:8080/login', {
            method:'POST',
            body: JSON.stringify(data),
          });
          //const data = await res.json();
          console.log(res);

      }
      fetchData();
      
  }, []);
  }

  function onChangeEmail(event) {
    setEmailState(event.target.value);
  }

  function onChangePassword(event) {
    setPasswordState(event.target.value);
  }

  return (
    <div className="auth-wrapper">
      <div className="leftHalf" />
      <div className="rightHalf">
        <form onSubmit={sendData()}>
          <h1>ConnectVille</h1>
          <h2>Hello again!</h2>
          <div className="mb-3 input-group">
            <input
              type="text"
              id="name"
              required
              className="input form-control"
              onChange={onChangeEmail}
              value={emailState}
            ></input>
            <label htmlFor="name" className="input-label">
              Email address
            </label>
          </div>
          <div className="mb-3 input-group">
            <input
              type="password"
              required
              className="input form-control"
              onChange={onChangePassword}
              value={passwordState}
            />
            <label htmlFor="name" className="input-label">
              Password
            </label>
          </div>
          <div className=".col-6 .col-md-4">
            <Button variant="outline-primary" onClick={sendData}>
              Log in
            </Button>{" "}
          </div>
        </form>
      </div>
    </div>
  );
}
