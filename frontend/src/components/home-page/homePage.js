import React from "react";
import "./homePage.css";
import Menu from "../menu/menu";
import MenuCard from "../home-card/home-card";
import LatestNews from "./latest-news/latest-news";
import { useEffect, useState } from "react";
import getNewsFeed from "../../api/getNewsFeed";
import getPostsPictures from "../../api/getPostsPictures";
import { postcss } from "autoprefixer";

export default function HomePage() {
  const [posts, setPosts] = useState([]);
  const [gotPosts, setGotPosts] = useState(false);
  useEffect(
    () => {
      const fetchData = async () => {
        if (!gotPosts) {
          const data = await getNewsFeed();
          setPosts(data);
          setGotPosts(true);
        }
      };
      fetchData();
    },
    [],
    false
  );

  return (
    <div>
      <Menu></Menu>
      <div className="container-grid">
        <div className="grid">
          {posts.map((post, index) => (
            <div className={"item" + (index + 1)}>
              <MenuCard
                title={post ? post.title : "Loading..."}
                description={post ? post.text : "Loading..."}
                id={post ? post.postId : "Loading..."}
              ></MenuCard>
            </div>
          ))}
        </div>
      </div>
      <LatestNews></LatestNews>
    </div>
  );
}
