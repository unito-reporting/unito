/*
 * HomeReducer
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

import { HIDE_ISSUE } from './constants';

// The initial state of the App
export const initialState = fromJS({
  issueToHide: '',
});

function homeReducer(state = initialState, action) {
  switch (action.type) {
    case HIDE_ISSUE:
      // Delete prefixed '@' from the github issueToHide
      return state.set('issueToHide', action.id);
    default:
      return state;
  }
}

export default homeReducer;
