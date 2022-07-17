import "./formTest.css";
import { useState } from "react";
import { Container } from "@mui/system";
import { TextField } from "@mui/material";
import { Button } from "@mui/material";
import { IconButton } from "@mui/material";

function FormTest() {
  const [inputFields, setInputFields] = useState([
    { firstName: "", lastName: "" },
  ]);
  const handleChangeInput = (index, event) => {
    const values = [...inputFields];
    values[index][event.target.name] = event.target.value;
    setInputFields(values);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log("InputFields", inputFields);
  };

  const handleAddFields = () => {
    setInputFields([...inputFields, { firstName: "", lastName: "" }]);
  };

  const handleRemoveFields = (index) => {
    const values = [...inputFields];
    values.splice(index, 1);

    setInputFields(values);
  };

  return (
    <Container>
      <h1>Add New Member</h1>
      <form onSubmit={handleSubmit}>
        {inputFields.map((inputFields, index) => (
          <div key={index}>
            <TextField
              name="firstName"
              label="First Name"
              variant="filled"
              value={inputFields.firstName}
              onChange={(event) => handleChangeInput(index, event)}
            />
            <TextField
              name="lastName"
              label="Last Name"
              variant="filled"
              value={inputFields.lastName}
            />
            <IconButton onClick={() => handleRemoveFields(index)}>-</IconButton>
            <IconButton onClick={() => handleAddFields()}>+</IconButton>
          </div>
        ))}
        <Button
          variant="contained"
          color="primary"
          type="submit"
          onClick={handleSubmit}
        >
          Submit
        </Button>
      </form>
    </Container>
  );
}

export default FormTest;
