async function deletar(elem){

    if( confirm('Deseja realmente deletar o arquivo?') ){

        let resposta = await requestUtils.post(
            window.location.href,
            {nomeArquivo : elem.getAttribute('data-nome')}
        );

        if( typeof(resposta) == 'object' && resposta.sucesso){
            alert('Arquivo de lote deletado com sucesso...');
            window.location.href = '';
        }else{
            alert(`Não foi possível deletar o arquivo, motivo:\n${resposta.mensagem}`);
        }

    }else{
        return false;
    }

}

function gerarProximoNomeLote(){
    try{

        var registros = document.querySelectorAll('.td-nome-lote');

		var ultimoLote = 0;
		
		registros.forEach( (r)=>{
		
			let numeroAtual = parseInt( r.innerText.split('_')[1] );
			
			if( ultimoLote < numeroAtual ){
				ultimoLote = numeroAtual;
			}
		
		});
		
		ultimoLote += 1;
		
		let proximoNomeLote = `Lote_${ultimoLote}`;
		
		document.getElementById('descricao').value = proximoNomeLote;

    }catch(e){
        console.error(e);
    }
}