import styles from "./cop-card.module.css";
import NavBar from "./cop-navbar.js";
import getPostsPicture from "./../../api/getPostsPictures.js";
import React, { useEffect, useState } from "react";
import getProfilePicture from "../../api/getProfilePicture.js";

function CopCard(postData) {
  const [profilePicture, setProfilePicture] = useState("");
  const [postPicture, setPostPicture] = useState("");

  useEffect(() => {
    const fetchData = async () => {
      const myPicture = await getPostsPicture(postData.props.postId);
      const profile = await getProfilePicture(postData.props.userId);
      setPostPicture(myPicture);
      setProfilePicture(profile);
    };

    fetchData();
  }, []);

  return (
    <div className={`${styles["card"]}`}>
      <ul className={`${styles["flex-column"]}`}>
        <li>
          <img
            src={profilePicture}
            alt="Avatar"
            className={`${styles["profile-picture"]}`}
          />
        </li>
        <ul className={`${styles["list-no-bull"]}`}>
          <li
            className={`${styles["font-family"]} ${styles["font-size-title"]}`}
          >
            {postData.props.title}
          </li>
          <li
            className={`${styles["font-family"]} ${styles["font-size-author"]}`}
          >
            posted by
            <span className={`${styles["font-family"]}`}> {postData.props.username}</span>
          </li>
        </ul>
      </ul>
      <center>
        {postPicture.length > 0 && (
          <img
            className={`${styles["img-props"]}`}
            src={postPicture}
            alt="Avatar"
          />
        )}
      </center>
      <p className={`${styles["spacing-p"]}`}>{postData.props.text}</p>
      <NavBar post={postData.props}></NavBar>
    </div>
  );
}
export default CopCard;
