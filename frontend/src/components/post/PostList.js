import React, { useEffect, useState } from "react";
import Post from "./Post";
import CopCard from "../cop-card/cop-card";
import Menu from "../menu/menu";
import { useParams } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import Modal from "react-modal";
import PostModal from "../modal/Modal";
import styles from "./post-list.module.css";

export default function PostList() {
  //localhost:8080/groups/posts/2
  const { group } = useParams();
  const navigate = useNavigate();
  const [open, openModal] = useState(false);
  const toggleModal = () => {
    openModal(!open);
  };
  const userId = document.cookie.replace(/(?:(?:^|.*;\s*)userId\s*\=\s*([^;]*).*$)|^.*$/, "$1");
  const [posts, setPosts] = useState([]);
  const [canSee, setCanSee] = useState(false);
  //const [users, setUsers] = useState([]);
  useEffect(() => {
    let lGroup = group.toLowerCase();
    if (group !== "hr" && group !== "tech" && group !== "legal") {
      navigate("/home");
    }
    let groupName = "4";
    switch (group.toLowerCase()) {
      case "hr":
        groupName = "1";
        break;
      case "tech":
        groupName = "2";
        break;
      case "legal":
        groupName = "3";
        break;
      default:
        groupName = "4";
    }
    fetch("http://localhost:8080/groups/followed/" + userId).then
    (res => res.json()).then(data => {
      let followedGroups = data.map(group => group.groupId);
      console.log(followedGroups);
      console.log("group nam este", groupName);
      followedGroups.forEach(element => {
        if (element == groupName) {
          setCanSee(true);
        }
        
      });
    }
    );
    



    fetch("http://localhost:8080/groups/posts/" + groupName)
      .then((response) => response.json())
      .then((data) => {
        setPosts(data);
      });
  }, []);

  return (
    <div>
      <Menu></Menu>
      <Modal
        isOpen={open}
        onClose={toggleModal}
        backdropOpacity={1}
        // className={`${styles["modal-bg"]}`}
      >
        <PostModal group={group} close={toggleModal} />
      </Modal>
      {canSee == true ?
      <div className={`${styles["align-cards"]}`}>
        <button
          className={`${styles["button-post"]}`}
          id="modal-open"
          onClick={toggleModal}
        >
          Create a new post to group {group}
        </button>
        {posts.map((post) => (
          <CopCard key={post.postId} props={post} />
        ))}
      </div>
      : <div
      className={`${styles["font-size-title-nav"]}`}
    >
      ⚠️ You are not allowed to see this group, you have to follow it first⚠️
    </div>
      
      
        /* <h2>we're in group {group}</h2>
      {canSee == true ?
      <div>
      <h3>
        <button id="modal-open" onClick={toggleModal}>
          Create a new post to {group.toUpperCase()} group
        </button>
      </h3>
      <Modal isOpen={open} onClose={toggleModal} backdropOpacity={1}>
        <PostModal group={group} close={toggleModal} />
      </Modal>
      {posts.map((post) => (
        <CopCard key={post.postId} props={post} />
      ))}
      </div>
      : <h2>You are not allowed to see this group, you have to follow it first</h2>} */}
    </div>
  );
}
