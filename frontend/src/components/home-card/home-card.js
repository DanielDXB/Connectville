import "./home-card.css";
import React, { useEffect } from "react";
import { useState } from "react";
import getPostsPictures from "../../api/getPostsPictures";

function MenuCard(props) {
  const [picture, setPicture] = useState("");

  useEffect(() => {
    const fetchData = async () => {
      const myPicture = await getPostsPictures(props.id);
      setPicture(myPicture);
    };
    fetchData();
  }, "");
  const imageUrl = { backgroundImage: `url(${picture})` };
  return (
    <div className={`card__container ${props.className}`} style={imageUrl}>
      <div className="card__text">
        <img src={props.imageURL} className={`half_fade`}></img>
        <div className="test">
          <p className={`card_title_format`}>{props.title}</p>
          <p className="card_content_format">{props.description}</p>
        </div>
      </div>
    </div>
  );
}

export default MenuCard;
