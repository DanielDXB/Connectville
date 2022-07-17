import React, { useState } from "react";
import "./post.css";
import HeartIcon from "./heart.svg";
import CommentIcon from "./chat.svg";

function Post(props) {
  return (
    <div className="post-container">
      <img
        className="profile-picture"
        src="https://media.pitchfork.com/photos/5c7d4c1b4101df3df85c41e5/1:1/w_800,h_800,c_limit/Dababy_BabyOnBaby.jpg"
      ></img>
      <p className="username">{props.props.createUser}</p>
      <p className="post-title"> {props.props.title}</p>
      <p className="post-text"> {props.props.text}</p>
      <img className="post-image" src={props.props.image} />
      <div className="like-button">
        <button type="button" className="btn btn-primary">
          <img src={HeartIcon} />
        </button>
      </div>
      <div className="comment-button">
        <button type="button" className="btn btn-primary">
          <img src={CommentIcon} />
        </button>
      </div>
    </div>
  );
}
export default Post;
