/*
 * HomePage
 *
 * This is the first thing users see of our App, at the '/' route
 */

import React from 'react';
import PropTypes from 'prop-types';
import { Helmet } from 'react-helmet';
import { FormattedMessage } from 'react-intl';
import { connect } from 'react-redux';
import { compose } from 'redux';
import { createStructuredSelector } from 'reselect';

import injectReducer from 'utils/injectReducer';
import injectSaga from 'utils/injectSaga';
import {
  makeSelectIssues,
  makeSelectLoading,
  makeSelectError,
} from 'containers/App/selectors';
import H1 from "components/H1";
import H2 from 'components/H2';
import IssuesList from 'components/IssueList';
import Section from './Section';
import messages from './messages';
import { loadIssues } from '../App/actions';
import { makeSelectUsername } from './selectors';
import reducer from './reducer';
import saga from './saga';
import {hideIssue} from "./actions";
import A from "../../components/A";

/* eslint-disable react/prefer-stateless-function */
export class HomePage extends React.PureComponent {
  /**
   * when initial state issueToHide is not null, submit the form to load issues
   */
  componentDidMount() {
    this.props.onStartLoad();
  }

  render() {
    const { loading, error, issues } = this.props;
    const issueListProps = {
      loading,
      error,
      issues,
    };

    return (
      <article>
        <Helmet>
          <title>Overview</title>
        </Helmet>
        <div>
          <H1>
            <FormattedMessage
              {...messages.issueCountOverview}
              values={{
                number: issues.length,
              }}
              />
          </H1>
          <Section>
            <IssuesList {...issueListProps} />
          </Section>
        </div>
      </article>

    );
  }
}

HomePage.propTypes = {
  loading: PropTypes.bool,
  error: PropTypes.oneOfType([PropTypes.object, PropTypes.bool]),
  issues: PropTypes.oneOfType([PropTypes.array, PropTypes.bool]),
  onStartLoad: PropTypes.func,
  onHideIssue: PropTypes.func
};

export function mapDispatchToProps(dispatch) {
  return {
    onHideIssue: evt => dispatch(hideIssue(evt.target.value)),
    onStartLoad: evt => {
      dispatch(loadIssues());
    },
  };
}

const mapStateToProps = createStructuredSelector({
  issues: makeSelectIssues(),
  issueToHide: makeSelectUsername(),
  loading: makeSelectLoading(),
  error: makeSelectError(),
});

const withConnect = connect(
  mapStateToProps,
  mapDispatchToProps,
);

const withReducer = injectReducer({ key: 'home', reducer });
const withSaga = injectSaga({ key: 'home', saga });

export default compose(
  withReducer,
  withSaga,
  withConnect,
)(HomePage);
