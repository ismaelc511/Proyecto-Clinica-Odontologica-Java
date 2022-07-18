window.addEventListener('load', function () {
    (function(){

      //con fetch invocamos a la API de odontologos con el método GET
      //nos devolverá un JSON con una colección de odontologos
      const url = '/pacientes';
      const settings = {
        method: 'GET'
      }

      fetch(url,settings)
      .then(response => response.json())
      .then(data => {
         //recorremos la colección de odontologos del JSON
         for(paciente of data){
            //por cada odontologo armaremos una fila de la tabla
            //cada fila tendrá un id que luego nos permitirá borrar la fila si eliminamos el odontologo
            var table = document.getElementById("pacienteTable");
            var pacienteRow =table.insertRow();
            let tr_id = 'tr_' + paciente.id;
            pacienteRow.id = tr_id;

            let deleteButton = '<button' +

                               ' id=' + '\"' + 'btn_delete_' + paciente.id + '\"' +

                               ' type="button" onclick="deleteBy('+ paciente.id+')"' +

                               'class="btn btn-danger btn_delete">' +

                               '&times' +

                               '</button>';

            let updateButton = '<button' +

                               ' id=' + '\"' + 'btn_id_' + paciente.id + '\"' +

                               ' type="button" onclick="findBy('+paciente.id+')"' +

                               ' class="btn btn-info btn_id">' +

                               paciente.id +

                               '</button>';

            //armamos cada columna de la fila
            //como primer columna pondremos el boton modificar
            //luego los datos del odontologo
            //como ultima columna el boton eliminar
            pacienteRow.innerHTML = '<td>' + updateButton + '</td>'+
                    '<td class=\"td_id\">' + paciente.id + '</td>' +
                    '<td class=\"td_nombre\">' + paciente.nombre.toUpperCase() + '</td>' +
                    '<td class=\"td_apellido\">' + paciente.apellido.toUpperCase() + '</td>'+
                    '<td class=\"td_matricula\">' + paciente.dni.toUpperCase() + '</td>' +
                    '<td>' + deleteButton + '</td>';

        };

      })
    })
    (function(){

         let pathname = window.location.pathname;

         if (pathname == "/pacienteList.html") {

             document.querySelector(".nav .nav-item a:last").addClass("active");

         }

       })
})
  
