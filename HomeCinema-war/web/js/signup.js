/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(function () {
    var date = new Date();
    var maxdate = (date.getUTCFullYear()) - 18 + "-" + (date.getMonth() + 1) + "-" + ((date.getDate() > 9) ? date.getDate() : "0" + date.getDate());
    var mindate = (date.getUTCFullYear()) - 120 + "-" + (date.getMonth() + 1) + "-" + ((date.getDate() > 9) ? date.getDate() : "0" + date.getDate());
    $("#birthDate").attr("max", maxdate);
    $("#birthDate").attr("min", mindate);
});