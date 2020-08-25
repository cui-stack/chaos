import React, {Component} from 'react'

const TableHead = (props) => {
    const myHead = props.Head.map((item, index) => {
        return <th key={index}>{item.header}</th>
    })
    return (
        <thead>
        <tr>
            {myHead}
        </tr>
        </thead>
    )
}

const TableBody = (props) => {
    const myBody = props.Body.map((item, index) => {
        return <tr>
            <td>{item.name}</td>
            <td>{item.job}</td>
        </tr>
    })
    return (
        <thead>
        {myBody}
        </thead>
    )
}


class Table extends Component {
    render() {
        const {Head} = this.props
        const {Body} = this.props
        return (
            <table>
                <TableHead Head={Head}/>
                <TableBody Body={Body}/>
            </table>
        )
    }
}

export default Table
