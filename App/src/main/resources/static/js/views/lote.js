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