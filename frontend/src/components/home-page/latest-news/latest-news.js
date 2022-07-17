import "./latest-news.css";
import LatestArticles from "../latest-articles/latest-articles";
import getPostsByGroup from "../../../api/getPostsByGroup";
import { useEffect, useState } from "react";
function LatestNews() {
  const [posts, setPosts] = useState([]);
  const [userGroups, setUserGroups] = useState([]);
  const [canSee, setCanSee] = useState(false);
  const handleAddArticle = async (category) => {
    const idCookie = document.cookie.replace(
      /(?:(?:^|.*;\s*)userId\s*\=\s*([^;]*).*$)|^.*$/,
      "$1"
    );
    fetch(`http://localhost:8080/groups/followed/${idCookie}`)
      .then((res) => res.json())
      .then((data) => {
        setUserGroups(data);
      });
    setCanSee(false);
    userGroups.forEach((group) => {
      if (group.title.toLowerCase() == category.toLowerCase()) {
        setCanSee(true);
      }
    });
    if (category == "ALL") setCanSee(true);
    if (canSee == true) {
      const data = await getPostsByGroup(category);
      setPosts(data);
    }
  };

  return (
    <div>
      <div className="container-latest-news">
        <div className="font-size-title-nav">Don't miss</div>
        <div>
          <button
            className="button-nav button-nav-col-all"
            onClick={() => handleAddArticle("ALL")}
          >
            All
          </button>
          <button
            className="button-nav button-nav-col-legal"
            onClick={() => handleAddArticle("LEGAL")}
          >
            Legal
          </button>
          <button
            className="button-nav  button-nav-col-hr"
            onClick={() => handleAddArticle("HR")}
          >
            HR
          </button>
          <button
            className="button-nav  button-nav-col-tech"
            onClick={() => handleAddArticle("TECH")}
          >
            Tech
          </button>
        </div>
        <div />
      </div>
      {canSee == true ? (
        <LatestArticles props={posts}></LatestArticles>
      ) : (
        <div>
          <div className="font-size-title-nav dont-miss-text ">
          ⚠️ You are not following this group ⚠️
          </div>
        </div>
      )}
    </div>
  );
}

export default LatestNews;
