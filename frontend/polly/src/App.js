import logo from './logo.svg';
import './App.css';
import Greet from './components/Greet'
import Profile from './components/Profile'
import Feed from './components/Feed'

function App() {
  return (
    <div className="App">
       <Greet/>
       <Profile/>
       <Feed/>
    </div>
  );
}

export default App;
