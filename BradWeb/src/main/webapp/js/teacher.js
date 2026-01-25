window.onload = function() {
	let clear = document.getElementById("clear");
	let myDrawer= document.getElementById("myDrawer");
	let webSocket = new WebSocket("ws://10.0.101.133:8080/BradWeb/mycenter");
	let isConnect = false;
	
	// 連線成功後，告訴server(/mycenter)我是老師
	webSocket.onopen = function(){
		isConnect = true;
		webSocket.send(JSON.stringify({isTeacher:true}))
	}
	// 關閉連線
	webSocket.onclose = function(){
		isConnect = false;
	}
	// 印出錯誤
	webSocket.onerror = function(){
		console.log("onError" + event);
	}
	
	//-------------------------------------------------
	
	// 取得畫圖工具
	let ctx = myDrawer.getContext("2d");
	let isDrag = false;
	// 滑鼠點下去開始畫線
	myDrawer.onmousedown = function(e){
		isDrag = true;
		let x = e.offsetX, y = e.offsetY;
		ctx.beginPath();
		ctx.lineWidth = 4;
		ctx.moveTo(x, y);
		
		let data = {
			isClear : false,
			isNewLine : true, 
			x : x,
			y : y
		};
		webSocket.send(JSON.stringify(data));
		
	}
	// 滑鼠放開停止畫線
	myDrawer.onmouseup = function(e){
		isDrag = false;
	}
	// 滑鼠移動持續畫線
	myDrawer.onmousemove = function(e){
		if (isDrag) {
			let x = e.offsetX, y = e.offsetY;
			ctx.lineTo(x, y);
			ctx.stroke();
			
			let data = {
				isClear : false,
				isNewLine : false, 
				x : x,
				y : y
			};
			webSocket.send(JSON.stringify(data));
		}
	}
	// 清除畫布
	clear.addEventListener("click", function(){
		ctx.clearRect(0, 0, myDrawer.width, myDrawer.height);
		let data = {
			isClear : true
		};
		webSocket.send(JSON.stringify(data));
	});
}
	
