import styles from "./cop-navbar.module.css";
import LikeIcon from "../../assets/like.png";
import LikedIcon from "../../assets/liked.png";
import CommentIcon from "../../assets/comment.png";
import PinIcon from "../../assets/pin.png";
import UnpinIcon from "../../assets/pinned.png";
import BinIcon from "../../assets/bin.png";
import { useEffect, useState } from "react";
import { data } from "autoprefixer";
import Modal from "react-modal";
import CommentModal from "../comment-modal/comment-modal";

function CopNavbar(post) {
  console.log("post: ", post);
  const [like, setLike] = useState(false);
  const [pin, setPin] = useState(false);
  const [role, setRole] = useState("");
  const [open, openModal] = useState(false);
  console.log(post);
  const toggleModal = () => {
    openModal(!open);
  };
  const userId = document.cookie.replace(
    /(?:(?:^|.*;\s*)userId\s*\=\s*([^;]*).*$)|^.*$/,
    "$1"
  );
  useEffect(() => {
    fetch("http://localhost:8080/like/liked/" + post.post.postId + "/" + userId)
      .then((res) => res.json())
      .then((data) => {
        setLike(data);
      });
    fetch("http://localhost:8080/users/" + userId)
      .then((res) => res.json())
      .then((data) => {
        setRole(data.role);
      });
    setPin(post.post.pinned);

      if (like == true) {
        document.getElementById(post.post.postId + "like").src = LikedIcon;
      } else {
        document.getElementById(post.post.postId + "like").src = LikeIcon;
      }
    

    if (role == "admin") {
      if (pin == true) {
        document.getElementById(post.post.postId + "pin").src = PinIcon;
      } else {
        document.getElementById(post.post.postId + "pin").src = UnpinIcon;
      }
    }
  }, [like]);

  function handleLikeEvent() {
    fetch(
      "http://localhost:8080/like/like/" + post.post.postId + "/" + userId,
      {
        method: "POST",
      }
    ).then((res) => {
      if (res.status == 200) {
        setLike(!like);
        if (like == true) {
          document.getElementById(post.post.postId + "like").src = LikedIcon;
        } else {
          document.getElementById(post.post.postId + "like").src = LikeIcon;
        }
      }
    });
  }

  function handlePinEvent() {
    fetch("http://localhost:8080/newsfeed/pin/" + post.post.postId, {
      method: "POST",
    }).then((res) => {
      if (res.status == 200) {
        if (role == "admin") {
          setPin(!pin);
          if (pin == true) {
            document.getElementById(post.post.postId + "pin").src = PinIcon;
          } else {
            document.getElementById(post.post.postId + "pin").src = UnpinIcon;
          }
        }
      }
    });
  }

  function handleDeleteEvent(){
    fetch("http://localhost:8080/post/delete/" + post.post.postId, {
      method: "DELETE",
    }).then((res) => {
      console.log(res);
      if (res.status == 200) {

        window.location.reload();
      }
    });
  }

  return (
    <div className={`${styles["container"]}`}>
      <button onClick={handleLikeEvent} className={`${styles["button-prop"]}`}>
        <img
          id={post.post.postId + "like"}
          src={LikeIcon}
          width={40}
          height={40}
        />
      </button>
      <button
        id="comment"
        onClick={toggleModal}
        className={`${styles["button-prop"]}`}
      >
        <img src={CommentIcon} width={40} height={40} />
      </button>
      <Modal
        isOpen={open}
        onClose={toggleModal}
        backdropOpacity={1}
        className={`${styles["modal-bg"]}`}
      >
        <CommentModal postId={post.post.postId} close={toggleModal} />
      </Modal>

      {role == "admin" && (
        <button onClick={handlePinEvent} className={`${styles["button-prop"]}`}>
          {pin==true ? 
          <img
            id={post.post.postId + "pin"}
            src={PinIcon}
            width={40}
            height={40}
          />
          :
          <img
            id={post.post.postId + "pin"}
            src={UnpinIcon}
            width={40}
            height={40}
          />}

        </button>
      )}
      {(role == "admin" || userId == post.post.userId) && (
        // <input type="image" id="bin" alt="bin_icon" src={BinIcon} />
        <button onClick={handleDeleteEvent} className={`${styles["button-prop"]}`}>
           <img
            id={post.post.postId + "bin"}
            src={BinIcon}
            width={40}
            height={40}
          />
        </button>
      )}
    </div>
  );
}

export default CopNavbar;
