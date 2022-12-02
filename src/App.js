import logo from './logo.svg';
import './App.css';
import React, {Component} from "react";
import Customer from "./components/Customer";

const customers=[
    {
    'id': 1,
    'image' : 'https://placeimg.com/64/64/1',
    'name' : '박매일',
    'birthday' : '9999999',
    'gender' : '남자',
    'job' : '대학생'
   },
    {
        'id': 2,
        'image' : 'https://placeimg.com/64/64/2',
        'name' : '김현주',
        'birthday' : '9999999',
        'gender' : '여자',
        'job' : '대학생'
    }
]

class App extends Component{
    render() {
      return(
          <div>
              { customers.map(c=>{ return( <Customer key={c.id} id={c.id} image={c.image} name={c.name} birthday={c.birthday} gender={c.gender} job={c.job}/>);})}
          </div>
        );
      }
    }

export default App;
