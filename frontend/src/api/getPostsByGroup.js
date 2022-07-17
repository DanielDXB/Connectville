export default async function getPostsByGroup(group) {
  const getData = async () => {
    let groupName = "";
    let data = [];
    switch (group) {
      case "HR":
        groupName = "1";
        break;
      case "TECH":
        groupName = "2";
        break;
      case "LEGAL":
        groupName = "3";
        break;
      default:
        groupName = "4";
    }

    if (groupName !== "4") {
      const res = await fetch(
        "http://localhost:8080/groups/posts/" + groupName
      );
      data = await res.json();
    } else {
      const res = await fetch("http://localhost:8080/post/all");
      data = await res.json();
    }

    return data;
  };

  let returnData;
  try {
    returnData = await getData();
  } catch (error) {
    console.error(error);
  }

  return returnData;
}
