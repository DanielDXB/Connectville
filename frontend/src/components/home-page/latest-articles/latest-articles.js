import styles from "./latest-articles.module.css";

function LatestArticles(props) {
  console.log(props);
  return (
    <div>
      {props.props.map((post, index) => (
        <div className={`${styles["font-latest-art"]}`}>
          <h2 className={`${styles["font-title-latest-art"]}`} id="title">
            {post.title}
          </h2>

          <p id="text">{post.text}</p>
          <h5 id="author" className={`${styles["h5-font"]}`}>
            Created by:{post.username}
          </h5>
        </div>
      ))}{" "}
    </div>
  );
}

export default LatestArticles;
