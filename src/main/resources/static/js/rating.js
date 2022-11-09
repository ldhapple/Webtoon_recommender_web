// function updateProgressBar(progressBar, value) {
//     value = Math.round(value);
//     progressBar.querySelector(".progress__fill").style.width = `${value}%`;
//     progressBar.querySelector(".progress__text").textContent = `${value}%`;
//   }
  
//   const myProgressBar = document.querySelector(".progress");
  
//   /* Example */
//   updateProgressBar(myProgressBar, 16);

//const toDoForm = document.getElementById("todo-form");
//
//function handleToDoSubmit() {
//
//}
//
//toDoForm.addEventListener("submit", handleToDoSubmit);

//function saveScrollPos(object){
//    document.getElementById('scrollPos').value = object.scrollTop;
//}
//
//function setScrollPos(){
//    document.getElementById('dvDeptList').scrollTop = document.getElementById('scrollPos').value;
//}

$(function(){
    $('#submit').on("click",function () {

        var form1 = $("#form").serialize();

        console.log(form1);
        $.ajax({
            type: "post",
            url: "/recommend/recommender",
            data: form1,
            dataType: 'json',
            success: function (data) {
                alert("success");
                console.log(data);
            },
            error: function (request, status, error) {
                console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);

            }
        });
    });
});