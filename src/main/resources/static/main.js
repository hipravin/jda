var canvas = document.getElementById("mainCanvas");
var ctx = canvas.getContext("2d");

var nodePositions = new Map();
var classGraph;
var selectedNode;

$(document).ready(function () {
    initCtx();
    loadSampleGraph();
    addCanvasOnClick();
});


function addCanvasOnClick() {
    var elem = document.getElementById('mainCanvas');
    var elemLeft = elem.offsetLeft + elem.clientLeft;
    var elemTop = elem.offsetTop + elem.clientTop;
    var context = elem.getContext('2d');
    var elements = [];

// Add event listener for `click` events.
//     elem.addEventListener('click', function (event) {
//         const rect = canvas.getBoundingClientRect();
//         const x = event.clientX - rect.left;
//         const y = event.clientY - rect.top;
//
//         // alert(`Clicked on ${x}, ${y}`);
//
//         nodePositions.forEach((v, k, m) => {
//             if (isNear(x, y, v.pos.x, v.pos.y)) {
//                 alert('Clicled on ' + k);
//             }
//         });
//     }, false);

    elem.addEventListener('mousedown', function (event) {
        const rect = canvas.getBoundingClientRect();
        const x = event.clientX - rect.left;
        const y = event.clientY - rect.top;

        nodePositions.forEach((v, k, m) => {
            if (isNear(x, y, v.pos.x, v.pos.y)) {
                selectedNode = k;
                console.log(`mousedown ${k}`);
            }
        });
    }, false);

    elem.addEventListener('mousemove', function (event) {
        const rect = canvas.getBoundingClientRect();
        const x = event.clientX - rect.left;
        const y = event.clientY - rect.top;

        const origx = rtx(x);
        const origy = rty(y);

        if (selectedNode) {
            classGraph.nodes.forEach((n, i) => {
                if(n.header === selectedNode) {
                    n.position.x = origx;
                    n.position.y = origy;
                }
                n.links.forEach((l, j) => {
                    if(l.nodeTo === selectedNode) {
                        l.positionTo.x = origx;
                        l.positionTo.y = origy;
                    }
                });
            });
            drawGraph(classGraph);
        }
    }, false);

    elem.addEventListener('mouseup', function (event) {
        selectedNode = null;
    }, false);
}

function isNear(xclicked, yclicked, nx, ny) {
    return Math.abs(nx - xclicked) < 10 && Math.abs(ny - yclicked) < 10;
}

function initCtx() {
    ctx.strokeStyle = "green";
    ctx.lineWidth = "1";

    ctx.fillStyle = "red";
    ctx.font = "9pt sans-serif";

}


function drawSomething() {


    drawNode(100, 100, 200, 50, '100 100 200 50');
    drawNode(300, 200, 50, 25, '300 200 50 25');


    drawArrow(100, 100, 300, 200);
}

function clearCanvas() {
    ctx.clearRect(0,0, canvas.clientWidth, canvas.clientHeight);
}

function drawNode(x, y, w, h, text) {

    ctx.strokeRect(x, y, w, h);
    ctx.fillText(text, x + 2, y + 10);
}

function drawArrow(fromx, fromy, tox, toy) {
    ctx.beginPath();

    var headlen = 10; // length of head in pixels
    var dx = tox - fromx;
    var dy = toy - fromy;
    var angle = Math.atan2(dy, dx);
    ctx.moveTo(fromx, fromy);
    ctx.lineTo(tox, toy);
    ctx.lineTo(tox - headlen * Math.cos(angle - Math.PI / 6), toy - headlen * Math.sin(angle - Math.PI / 6));
    ctx.moveTo(tox, toy);
    ctx.lineTo(tox - headlen * Math.cos(angle + Math.PI / 6), toy - headlen * Math.sin(angle + Math.PI / 6));
    ctx.stroke();
}

/*
{
  "nodes": [
    {
      "position": {
        "x": 70.42385478545998,
        "y": -9.464415467569225
      },
      "value": 38,
      "header": "hipravin.samples.chess.engine.model.piece.Knight",
      "links": [
        {
          "positionTo": {
            "x": 29.628157786597228,
            "y": 35.48766019922081
          },
          "value": 1,
          "text": null,
          "nodeTo": "java.util.Set"
        },
        {
          "positionTo": {
            "x": 68.94476355832978,
            "y": 36.247060022119655
          },
          "value": 1,
          "text": null,
          "nodeTo": "hipravin.samples.chess.engine.model.PieceColor"
        },

 */

function loadSampleGraph() {
    $.ajax({
        type: "GET",
        url: "/api/sample/",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (data) {
            drawGraph(data);
        },
        error: function (errMsg) {
            alert(errMsg);
        }
    });
}

function drawGraph(graph) {

    clearCanvas();
    classGraph = graph;
    graph.nodes.forEach((n, i) => {
        drawNodeAndLinks(n);
    });
}

function drawNodeAndLinks(node) {
    if (!node.nonProjectClass && node.header.indexOf('$') < 0) {
        const width = 7 * node.header.length;
        drawGraphNode(node, width);
        node.links.forEach((l, i) => {
            drawGraphLink(node.position, l, width);
        });
    }
}

function drawGraphNode(node, width) {
    drawNode(tx(node.position.x), ty(node.position.y), width, scaleH(node), node.header);
    nodePositions.set(node.header, {
        pos: {
            x: tx(node.position.x),
            y: ty(node.position.y)
        }
    });
    // nodePositions[node.header].pos.x = tx(node.position.x);
    // nodePositions[node.header].pos.y = ty(node.position.y);
}

function drawGraphLink(from, link, width) {
    if (!link.nonProjectClass && link.nodeTo.indexOf('$') < 0) {
        drawArrow(tx(from.x) + width, ty(from.y), tx(link.positionTo.x), ty(link.positionTo.y));
    }
}

function tx(x) {
    return (x + 100) * ((canvas.clientWidth - 300) / 200);
}

function rtx(tx) {
    return tx / ((canvas.clientWidth - 300) / 200) - 100;
}

function ty(y) {
    return (y + 100) * ((canvas.clientHeight - 20) / 200);
}

function rty(ty) {
    return ty / ((canvas.clientHeight - 20) / 200) - 100;

}

function scaleH(node) {
    return 15 + Math.sqrt(node.value / 3);
}
