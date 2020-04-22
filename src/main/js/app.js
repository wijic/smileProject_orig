const React = require('react');
const ReactDOM = require('react-dom');
const client = require('./client');

class App extends React.Component {

	constructor(props) {
		super(props);
		this.state = {smileArray: []};
	}

	loadJokes = (newJokeList) => {
		this.setState({smileArray: newJokeList});
	}

	render() {
		return (
			<div>
				<div className="header">Smile Project</div>
				<SmileForm onSubmit={this.loadJokes}/>
				<SmileList smileList={this.state.smileArray}/>
			</div>
		)
	}
}

class SmileForm extends React.Component {
	textBoxInput = React.createRef();
	handleSubmit = (textBoxEvent) => {
		textBoxEvent.preventDefault();

		client({method: 'GET', path: `/api/jokes?term=${this.textBoxInput.current.value}`}).then(response => {
			this.props.onSubmit(response.entity.results);
		});
	}
	render () {
		return (
			<form onSubmit={this.handleSubmit}>
				<input type="text" placeholder="Enter a keyword here" ref={this.textBoxInput} required />
				<button>Search</button>
			</form>
		);
	}
}

class SmileList extends React.Component{
	render() {
		const jokeArray = this.props.smileList.map(jokeElement =>
			<Smile key={jokeElement.id} data={jokeElement}/>
		);
		return (
			<div>
				{jokeArray}
			</div>
		)
	}
}

class Smile extends React.Component{
	render() {
		return (
			<div className="joke">{this.props.data.joke}</div>
		)
	}
}

ReactDOM.render(
<App />,
	document.getElementById('react')
)