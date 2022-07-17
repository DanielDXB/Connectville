import React from "react";
import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
import "./App.css";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import { Navigate } from "react-router-dom";
import Login from "./components/login/login";
import HomePage from "./components/home-page/homePage";
import Modal from "./components/modal/Modal";
import Card from "./components/cop-card/cop-card"
import PostList from "./components/post/PostList";
import GroupCard from "./components/group-card/GroupCard";
import GroupPage from "./components/group-page/group-page";
import Newsfeed from "./components/newsfeed/newsfeed";

function App() {
  return (
    <Router>
      <div className="App">
        <Routes>
          
          <Route path="/login" element={<Login />} />
          <Route path="/modal" element={<Modal />} />
          <Route path="/home" element={<HomePage />} />
          <Route path="/card" element={<Card />} />
          <Route path="/posts/:group" element={<PostList/>} />
          <Route path="/newsfeed" element={<Newsfeed/>} />
          <Route path="/groupCard" element={<GroupCard/>} />
          <Route path="/groups" element={<GroupPage/>} />
          <Route exact path="/" element ={<HomePage/>} />
        </Routes>
      </div>
    </Router>
  );
}
export default App;
