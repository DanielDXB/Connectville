import React, { useEffect, useState } from "react";
import Post from "../post/Post";
import CopCard from "../cop-card/cop-card";
import Menu from "../menu/menu";
import { useParams } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import Modal from "react-modal";
import PostModal from "../modal/Modal";
import styles from "./newsfeed.module.css";

export default function Newsfeed() {
  //localhost:8080/groups/posts/2
  const { group } = useParams();
  const [posts, setPosts] = useState([]);
  const [role, setRole] = useState("");
  const navigate = useNavigate();
  const [open, openModal] = useState(false);
  const toggleModal = () => {
    console.log("am toggleit modala");
    openModal(!open);
  };

  const userId = document.cookie.replace(
    /(?:(?:^|.*;\s*)userId\s*\=\s*([^;]*).*$)|^.*$/,
    "$1"
  );

  useEffect(() => {
    fetch("http://localhost:8080/newsfeed/all")
      .then((response) => response.json())
      .then((data) => {
        setPosts(data);
      });
    fetch("http://localhost:8080/users/" + userId)
      .then((res) => res.json())
      .then((data) => {
        setRole(data.role);
        console.log(data.role);
      });
  }, []);

  return (
    <div>
      <Menu></Menu>
      <div className={`${styles["align-cards"]}`}>
        {role == "admin" && (
          <div>
            <button
              className={`${styles["button-post"]}`}
              id="modal-open"
              onClick={toggleModal}
            >
              Create a new post
            </button>
            <Modal isOpen={open} onClose={toggleModal} backdropOpacity={1}>
              <PostModal group={"newsfeed"} close={toggleModal} />
            </Modal>
          </div>
        )}
        {posts.map((post) => (
          <CopCard key={post.postId} props={post} />
        ))}
      </div>
    </div>
  );
}
