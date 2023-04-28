# NCPF Specifications
*For Legacy NCPF versions, see [NCPF Format and Configuration Guidelines](https://docs.google.com/document/d/1dzU2arDrD7n9doRua8laxzRy9_RtX-cuv1sUJBB5aGY/edit#heading=h.a5zsimflpf1b)*

## File Format
NCPF is no longer just a file type, but rather a data structure, and can be saved in any file format that supports it.
Files should have a dual extension, with `.ncpf` followed by the extension of the format (For example, `.ncpf.json`)

Any file format that supports the neccesary data structure is supported.

## Primitive Types
- Object (Contains String-keyed elements in no particular order)
- Boolean
- String
- Integer (32 bit signed)
- Float (32 bit)
- List (An ordered list of elements, contains many items of one type)

## Complex Types
Commonly used types within NCPF that are standardized and defined here
