export default async function getProfilePicture(userId) {
    console.log(userId);
    const getData = async() =>{
            
            const res = await fetch('http://localhost:8080/image/getProfile/'+userId);
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