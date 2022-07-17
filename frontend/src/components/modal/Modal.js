import React, { useState } from "react";
import "./modal.css";

export default function PostModal(props) {
  const [titleState, setTitleState] = useState("");
  const [textState, setTextState] = useState("");
  const [postIdState, setPostIdState] = useState("");
  const [fileState, setFileState] = useState(null);
  const userId = document.cookie.replace(
    /(?:(?:^|.*;\s*)userId\s*\=\s*([^;]*).*$)|^.*$/,
    "$1"
  );
  console.log(props);
  let groupName = "";
  switch (props.group.toLowerCase()) {
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
  function sendData() {
    var raw = JSON.stringify({
      title: titleState,
      topic: "#topic",
      text: textState,
      createTimestamp: "2022-12-11T11:40:49Z",
      createUser: userId,
    });
    var myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");

    var requestOptions = {
      // mode:'no-cors',
      method: "POST",
      headers: myHeaders,
      body: raw,
      //redirect: 'follow'
    };
    fetch("http://localhost:8080/post/new/" + groupName, requestOptions)
      .then((response) => response.text())
      .then((result) => {
        console.log(result);
        if (fileState) {
          var formdata2 = new FormData();
          console.log(fileState[0].name);
          formdata2.append("picture", fileState[0]);

          var requestOptions = {
            method: "POST",
            body: formdata2,
          };

          fetch("http://localhost:8080/post/picture/" + result, requestOptions)
            .then((response) => response.text())
            .then((result) => console.log(result))
            .catch((error) => console.log("error", error));
        }
      })
      .catch((error) => console.log("error", error));
    console.log(postIdState);
  }

  function onChangeTitle(event) {
    setTitleState(event.target.value);
  }

  function onChangeText(event) {
    setTextState(event.target.value);
  }

  function onChangeFile(event) {
    setFileState(event.target.files);
    console.log(event.target.files);
  }

  return (
    <div className="modal-overlay">
      <div className="modal-box">
      {/* <div> */}
        <button className="button-prop" onClick={props.close}>
          X
        </button>
        <div className="container-modal">
          <form className="form-modal" onSubmit={sendData}>
            <div className="intro"> Create a Post</div>
            <div className="title">
              <span className="title-span">Title:</span>
              <input
                type="text"
                required
                className="text--input"
                placeholder="Write the title here."
                onChange={onChangeTitle}
                value={titleState}
              ></input>
            </div>
            <div className="text">
              <span className="text-span">Text:</span>
              <textarea
                className="textarea"
                required
                name="content"
                placeholder="Please insert the text here."
                onChange={onChangeText}
                value={textState}
              ></textarea>
            </div>
            <div className="file">
              <span className="file-span">Upload picture:</span>
              <input
                type="file"
                className="file--input"
                id="file"
                onChange={onChangeFile}
              ></input>
            </div>
            <div className="buttons-modal">
              <button className="btn--post">Cancel</button>
              <button className="btn--post">Post </button>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
}
