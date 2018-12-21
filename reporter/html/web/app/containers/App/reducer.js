/*
 * AppReducer
 *
 * The reducer takes care of our data. Using actions, we can change our
 * application state.
 * To add a new action, add it to the switch statement in the reducer function
 *
 * Example:
 * case YOUR_ACTION_CONSTANT:
 *   return state.set('yourStateVariable', true);
 */

import { fromJS } from 'immutable';

import { LOAD_ISSUES_SUCCESS, LOAD_ISSUES, LOAD_ISSUES_ERROR } from './constants';

// The initial state of the App
const initialState = fromJS({
  loading: false,
  error: false,
  userData: {
    issues: false,
  },
});

function appReducer(state = initialState, action) {
  switch (action.type) {
    case LOAD_ISSUES:
      return state
        .set('loading', true)
        .set('error', false)
        .setIn(['userData', 'issues'], false);
    case LOAD_ISSUES_SUCCESS:
      return state
        .setIn(['userData', 'issues'], action.issues)
        .set('loading', false);
    case LOAD_ISSUES_ERROR:
      return state.set('error', action.error).set('loading', false);
    default:
      return state;
  }
}

export default appReducer;
