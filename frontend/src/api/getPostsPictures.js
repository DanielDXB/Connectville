export default async function getNewsFeed(postId) {
  console.log(postId);
  const getData = async () => {
    const res = await fetch("http://localhost:8080/image/get/" + postId);
    if(res.status === 404){
      return "";
    }
    const imageBlob = await res.blob();
    const imageObjectURL = URL.createObjectURL(imageBlob);
    return imageObjectURL;
  };

  let returnData;
  try {
    returnData = await getData();
  } catch (error) {
    console.error(error);
  }
  return returnData;
}
