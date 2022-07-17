import React, { useEffect, useState } from "react";
import "./group-card.css";
import { useNavigate } from "react-router-dom";
function GroupCard() {
  const [notFollowed, setNotFollowed] = useState([], true);
  const [followedGroups, setFollowedGroups] = useState([], true);
  const idCookie = document.cookie.replace(
    /(?:(?:^|.*;\s*)userId\s*\=\s*([^;]*).*$)|^.*$/,
    "$1"
  );
  const fetchData = async () => {
    const res = await fetch(
      "http://localhost:8080/groups/followed/" + idCookie
    );
    const followed = await res.json();
    const res2 = await fetch(
      "http://localhost:8080/groups/not-followed/" + idCookie
    );
    const notFoll = await res2.json();

    for (let i = 0; i < notFoll.length; i++) {
      if (notFoll[i].title == "Newsfeed") {
        notFoll.splice(i, 1);
      }
    }
    setNotFollowed(notFoll);
    setFollowedGroups(followed);
  };
  useEffect(() => {
    fetchData();
  }, []);

  const groupAction = (group, status) => {
    console.log("am intrat pe group action", group, status);
    if (status == true) unfollowGroup(group);
    else followGroup(group);
  };

  function followGroup(groupId) {
    const follow = async () => {
      await fetch(
        "http://localhost:8080/groups/follow/" + groupId + "/" + idCookie,
        { method: "POST" }
      ).then((res) => {
        console.log(res);
      });
      const res1 = await fetch(
        "http://localhost:8080/groups/followed/" + idCookie
      );
      const followed = await res1.json();
      const res2 = await fetch(
        "http://localhost:8080/groups/not-followed/" + idCookie
      );
      const unfollowed = await res2.json();
      setFollowedGroups(followed);
      setNotFollowed(unfollowed);
    };
    follow();
  }

  function unfollowGroup(groupId) {
    const unfollow = async () => {
      await fetch(
        "http://localhost:8080/groups/unfollow/" + groupId + "/" + idCookie,
        { method: "POST" }
      );
      const res1 = await fetch(
        "http://localhost:8080/groups/followed/" + idCookie
      );
      const followed = await res1.json();
      const res2 = await fetch(
        "http://localhost:8080/groups/not-followed/" + idCookie
      );
      const unfollowed = await res2.json();
      setFollowedGroups(followed);
      setNotFollowed(unfollowed);
    };
    unfollow();
  }

  const navigate = useNavigate();
  const test = (group) => {
    navigate("/posts/" + group.toLowerCase());
  };

  return (
    <div className="container-group">
      {followedGroups.length > 0 && (
        <div className="followed">
          <div className="title-group">Followed groups</div>
          {followedGroups.map((group, index) => (
            <div className="container-box">
              <span className="single-title" onClick={() => test(group.title)}>
                {group.title}
              </span>
              <button
                className="group-btn"
                onClick={() => groupAction(group.groupId, true)}
              >
                Unfollow
              </button>
            </div>
          ))}
        </div>
      )}
      {notFollowed.length > 0 && (
        <div className="other">
          <div className="title-group">Other groups</div>
          {notFollowed.map(
            (group, index) =>
              group.title != "Newsfeed" && (
                <div className="container-box">
                  <span
                    className="single-title"
                    onClick={() => test(group.title)}
                  >
                    {group.title}
                  </span>
                  <button
                    className="group-btn"
                    onClick={() => groupAction(group.groupId, false)}
                  >
                    Follow
                  </button>
                </div>
              )
          )}
        </div>
      )}
    </div>
  );
}

export default GroupCard;
