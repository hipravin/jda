$(document).ready(function () {
    var drawing = document.getElementById("mainCanvas");
    var ctx = drawing.getContext("2d");

    ctx.strokeStyle = "green";
    ctx.lineWidth = "10";

    ctx.fillStyle = "red";
    ctx.font = "10pt sans-serif";

    drawNode(ctx, 100, 100, 200, 50, '100 100 200 50');
    drawNode(ctx, 300, 200, 50, 25, '300 200 50 25');

    ctx.beginPath();
    drawArrow(ctx, 100, 100 , 300 , 200);
    ctx.stroke();
});


function drawNode(ctx, x, y, w, h, text) {

    ctx.strokeRect(x, y, w, h);
    ctx.fillText(text, x + 10, y + 20);
}

function drawArrow(context, fromx, fromy, tox, toy) {
    var headlen = 10; // length of head in pixels
    var dx = tox - fromx;
    var dy = toy - fromy;
    var angle = Math.atan2(dy, dx);
    context.moveTo(fromx, fromy);
    context.lineTo(tox, toy);
    context.lineTo(tox - headlen * Math.cos(angle - Math.PI / 6), toy - headlen * Math.sin(angle - Math.PI / 6));
    context.moveTo(tox, toy);
    context.lineTo(tox - headlen * Math.cos(angle + Math.PI / 6), toy - headlen * Math.sin(angle + Math.PI / 6));
}
