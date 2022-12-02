import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();

// 리액트(React)에서 컴포넌트(Component)는 자바스크립트 함수(Function)와 흡사하다고 보면된다.
// 또한 이러한 컴포넌트는 프롭스(Props)를 입력으로 받아서 리액트 요소(React Element)를 반환하는
// 형태로 동작합니다. 이러한 구조를 따르면 사용자가 보는 웹 페이지의 화면을 효과적으로 구성할 수 있습니다.
// 프롭스(Props)는 속성(Property)의 약자입니다. 흔히 자바, 파이썬등의 객체지향프로그래밍에서
// 사용하는 그 '속성(attribute)'의 의미와 동일하다고 보시면 이해하기 쉽습니다.

// 리액트의 상태(state)란? state는 고정적인 데이터가 아니라 변경될 수 있는 데이터를 처리할 때 효율적으로
// 으로 사용될 수 있습니다. 재미있는 점은 실제로 state 값을 변경해서 화면(View)이 변경되면
// render()함수가 다시 실행되어 실제로 화면에 적용을 해준다는 점입니다.
// state를 사용하기 위해서는 함수형 컴포넌트 대신 클래스형 컴포넌트를 사용해야 된다.
// props만 이용하고자 한다면 함수형 컴포넌트를 그대로 사용하고 내부 객체의 값이 변경될 여지가 있다면
// 클래스형 컴포넌트로 state를 사용하고, 그렇지 않다면 props를 사용하면 된다.
