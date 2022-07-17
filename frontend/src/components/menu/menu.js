import React, { useEffect, useState } from "react";
import "./menu.css";
import { useNavigate } from "react-router-dom";
import { useCookies } from "react-cookie";
export const Meniu = () => {
  const navigate = useNavigate();
  const [cookies, setCookie, removeCookie] = useCookies([]);
  const usernameCookie = document.cookie.replace(
    /(?:(?:^|.*;\s*)username\s*\=\s*([^;]*).*$)|^.*$/,
    "$1"
  );
  console.log(cookies["userId"]);
  const [loggedIn, setLoggedIn] = useState(false);
  useEffect(() => {
    if (usernameCookie.length < 1 && usernameCookie == "undefined") {
      navigate("/login");
    } else {
      setLoggedIn(true);
    }
  }, false);

  const navigateHome = () => {
    navigate("/home");
  };

  const navigateGroups = () => {
    navigate("/groups");
  };

  const navigateNewsfeed = () => {
    navigate("/newsfeed");
  };
  const logout = () => {
    setCookie("userId", "");
    setCookie("username", "");
    navigate("/login");
  };
  return (
    <div className="container">
      <div className="buttons">
        <button className="btn" onClick={navigateHome}>
          Home
        </button>
        <button className="btn" onClick={navigateGroups}>
          Groups
        </button>
        <button className="btn" onClick={navigateNewsfeed}>
          Newsfeed
        </button>
        <button className="btn" onClick={logout}>
          Logout
        </button>
      </div>
      <div className="display-menu-logo-name">
        <div className="user-hello"> Hello, {usernameCookie}!</div>
        <div className="nume">ConnectVille</div>
      </div>
    </div>
  );
};

export default Meniu;
