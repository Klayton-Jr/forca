import logo from './logo.svg';
import './App.css';
import * as SockJS from 'sockjs-client';
import * as Stomp from 'stompjs';

function App() {
  const wsocket = new SockJS('/forca-game');
  const client = Stomp.over(wsocket);

 // client.connect({}, function(frame) {
 //   client.subscribe('/topic/messages', function(message) {
 //     console.log(JSON.parse(message.body));
 //   })
 // })

  //client.send("/app/hello", {}, JSON.stringify({'name': 'teste de nome'}));


  return (
    <SockJS url='http://localhost:8080/forca-game/'
    topics={['/topic/user']}
    onConnect={() => {
        console.log("connected");
    }}
    onDisconnect={() => {
        console.log("Disconnected");
    }}
    onMessage={(msg) => {
        console.log(msg);
    }}
    ref={(client) => {
        this.clientRef = client
    }}
    />
  );
}

export default App;
