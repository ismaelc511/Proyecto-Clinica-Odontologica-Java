window.addEventListener('load', function () {

   const formulario = document.querySelector('#update_odontologo_form');



   formulario.addEventListener('submit', function (event) {

       let paciente_id = document.querySelector('#paciente_id').value;



       //creamos un JSON que tendrá los datos de paciente

       //a diferencia de una película nueva, en este caso enviamos el ID

       //para poder identificarla y modificarla para no cargarla como nueva

       const formData = {

           id: document.querySelector('#nombre').value,

           titulo: document.querySelector('#apellido').value,

           categoria: document.querySelector('#dni').value,



       };



       //invocamos utilizando la función fetch la API peliculas

       //con el método PUT que modificará

       //la película que enviaremos en formato JSON

       const url = '/pacientes';

       const settings = {

           method: 'PUT',

           headers: {

               'Content-Type': 'application/json',

           },

           body: JSON.stringify(formData)

       }

         fetch(url,settings)

         .then(response => response.json())



   })

})



   //Es la función que se invoca
   //cuando se hace clic sobre el ID de una película del listado

   //se encarga de llenar el formulario con los datos de la película

   //que se desea modificar

   function findBy(id) {

         const url = '/pacientes'+"/"+id;

         const settings = {

             method: 'GET'

         }

         fetch(url,settings)

         .then(response => response.json())

         .then(data => {

             let paciente = data;

             document.querySelector('#id').value = paciente.id;
             document.querySelector('#nombre').value = paciente.nombre;
             document.querySelector('#apellido').value = paciente.apellido;
             document.querySelector('#dni').value = paciente.matricula;


             //el formulario por default está oculto y al editar se habilita

             document.querySelector('#div_paciente_updating').style.display = "block";

         }).catch(error => {

             alert("Error: " + error);

         })

     }