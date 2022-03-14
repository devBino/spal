function aplicarSelect2(){

    try{
    
        setTimeout(function(){
            
            var campos = $('select')
    
            for( const campo of campos ){
                $(campo).addClass('class-select')
                $(campo).select2()
            }

            resolve(true)
        },500)
        
    }catch(e){
        console.error(e)
        reject(false)
    }

}

aplicarSelect2();