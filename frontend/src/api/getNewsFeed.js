export default async function getNewsFeed(type) {
  const getData = async () => {
    const res = await fetch("http://localhost:8080/newsfeed/");
    const data = await res.json();
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
