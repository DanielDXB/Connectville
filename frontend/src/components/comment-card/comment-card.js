import styles from "./comment-card.module.css";
import React, { useEffect, useState } from "react";
import stylesCopCard from "../cop-card/cop-card.module.css";
import getProfilePicture from "../../api/getProfilePicture";

function CommentCard(props) {
  console.log(props.comment);
  const [profilePic, setProfilePic] = useState("");
  useEffect(() => {
    const fetchData = async () => {
      const profilePic = await getProfilePicture(props.comment.userId);
      setProfilePic(profilePic);
    };
    fetchData();
  }, []);

  return (
    <div className={`${props.className}`}>
      <div>
        <div className={`${styles["flex-direction"]}`}>
          <img
            src={profilePic}
            alt="Avatar"
            className={`${stylesCopCard["profile-picture"]}`}
          />
          <div className={`${styles["font-family"]}`}>
            {props.comment.username}
          </div>
        </div>
      </div>
      <p className={styles["align-p"]}>{props.comment.text}</p>
    </div>
  );
}

export default CommentCard;
