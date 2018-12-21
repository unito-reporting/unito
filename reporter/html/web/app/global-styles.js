import {createGlobalStyle} from 'styled-components';
import fonts from './fonts/font.css';

const GlobalStyle = createGlobalStyle(`
  html,
  body {
    height: 100%;
    width: 100%;
    background: #eceff1;
  }

  body {
    font-family: 'Roboto', 'Helvetica Neue', Helvetica, Arial, sans-serif;
  }

  #app {
    min-height: 100%;
    min-width: 100%;
  }

  p,
  label {
    line-height: 1.5em;
  }
` + fonts);

export default GlobalStyle;
