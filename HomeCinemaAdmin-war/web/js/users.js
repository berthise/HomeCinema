/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function ActiveForm() {
    if (confirm("Voulez-vous changer la couleur de fond de page ?")) { // Clic sur OK
        document.getElementById("email").setAttribute("disable", "true");
    }
}