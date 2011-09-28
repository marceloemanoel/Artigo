<script type="text/javascript">
    function associarContato(contatoId){
      $.ajax({
        url: "associarContato",
        data: {
          "contato.id":contatoId,
          "grupo.id": "${grupoInstance.id}"
        },
        dataType: "json",
        type: "POST"
      });
    }        
</script>
