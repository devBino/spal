var requestUtils = {

    post:function(url, parans, headers={"Content-Type": "application/json"}){
        return new Promise((resolve)=>{
            fetch(url, {
                method: "POST",
                headers,
                body: JSON.stringify(parans),
            })            
            .then((response) => response.json())
            .then((data) => {
                resolve(data);
            })
            .catch((error) => {
                resolve(false);
            });
        })

    }

}