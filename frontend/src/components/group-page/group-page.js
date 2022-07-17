import GroupCard from "../group-card/GroupCard";
import Menu from "../menu/menu";
import { useNavigate } from "react-router-dom";
import styles from "./group-page.module.css";

export default function GroupPage() {
  const navigate = useNavigate();
  const navigateHr = () => {
    navigate("/posts/hr");
  };

  const navigateLegal = () => {
    navigate("/posts/legal");
  };

  const navigateTech = () => {
    navigate("/posts/tech");
  };

  const navigateNewsfeed = () => {
    navigate("/newsfeed");
  };

  return (
    <div>
      <Menu></Menu>
      <div className={`${styles["center-follow-modal"]}`}>
        <GroupCard></GroupCard>
      </div>
    </div>
  );
}
