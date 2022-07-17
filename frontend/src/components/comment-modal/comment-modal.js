import styles from "./comment-modal.module.css";
import CommentCard from "../comment-card/comment-card";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

function CommentModal(props) {
  const [comments, setComments] = useState([]);
  const [text, setText] = useState("");
  console.log(props);

  const navigate = useNavigate();
  const userId = document.cookie.replace(
    /(?:(?:^|.*;\s*)userId\s*\=\s*([^;]*).*$)|^.*$/,
    "$1"
  );
  useEffect(() => {
    fetch("http://localhost:8080/all-comments-from-post/" + props.postId)
      .then((res) => res.json())
      .then((data) => {
        setComments(data);
      });
  }, []);

  function onChangeText(event) {
    setText(event.target.value);
  }

  function postComment() {
    var raw = JSON.stringify({
      userId: userId,
      postId: props.postId,
      text: text,
    });
    var myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");

    var requestOptions = {
      method: "POST",
      headers: myHeaders,
      body: raw,
    };

    fetch("http://localhost:8080/new-comment", requestOptions)
      .then((response) => response.text())
      .then((result) => {
        console.log(result);
        comments.push(result);
      });

    window.location.reload(false);
  }

  return (
    <div className={`${styles["modal-overlay"]} ${props.className}`}>
      <button className={`${styles["button-prop"]}`} onClick={props.close}>
        X
      </button>
      <div className={`${styles["modal-box"]}`}>
        <div className={styles["input-container"]}>
          <textarea
            maxLength={280}
            className={`${styles["text-area"]}`}
            placeholder="Write here..."
            onChange={onChangeText}
          ></textarea>
          <button
            className={`${styles["btn-post"]} ${styles["font-family"]}`}
            onClick={postComment}
          >
            POST
          </button>
        </div>
        <div className={styles["comment-list"]}>
          {comments.length > 0 ? (
            comments.map((comment) => {
              return (
                <CommentCard
                  className={styles["comment"]}
                  comment={comment}
                ></CommentCard>
              );
            })
          ) : (
            <h2>No comments yet:(</h2>
          )}
        </div>
      </div>
    </div>
  );
}

export default CommentModal;
