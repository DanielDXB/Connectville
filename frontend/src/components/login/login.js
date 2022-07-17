import React, { useState } from "react";
import Button from "react-bootstrap/Button";
import "./login.css";
import { useNavigate } from "react-router-dom";
import {useCookies} from 'react-cookie';

export default function Login() {
  const [emailState, setEmailState] = useState("");
  const [passwordState, setPasswordState] = useState("");
  const navigate = useNavigate();
  const [cookies, setCookie, removeCookie] = useCookies([]);

  function sendData() {
    var myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");

    const data = { email: emailState, password: passwordState };
    const raw = JSON.stringify(data);

    var requestOptions = {
      method: "POST",
      headers: myHeaders,
      body: raw,
      redirect: "follow",
    };

    fetch("http://localhost:8080/login", {
      headers: {
        "Content-Type": "application/json",
      },
      method: "POST",
      body: raw,
    })
      .then((response) => response.json())
      .then((result) => {
        console.log(result);
        if(result.username != "wrong") {
        setCookie("userId", result.userId);
        setCookie("username", result.username);

        navigate("/home");
        }
      });
  }

  function onChangeEmail(event) {
    setEmailState(event.target.value);
    console.log(event.target.value);
  }

  function onChangePassword(event) {
    setPasswordState(event.target.value);
  }

  return (
    <div className="auth-wrapper">
      <div className="leftHalf" />
      <div className="rightHalf">
        <form className="logintestform">
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
